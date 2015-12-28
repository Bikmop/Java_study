package com.bikmop.store;

import com.bikmop.petclinic.client.Client;
import com.bikmop.petclinic.pet.PetFactory;
import com.bikmop.petclinic.pet.PetType;
import com.bikmop.petclinic.pet.SomePet;
import com.bikmop.service.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Реализация клиники домашних животных на основе SQL базы данных
 */
public class JdbcClinic implements PetClinic {
    /** Соединение с БД */
    private final Connection connection;
    /** Текущий клиент для редактирования */
    private Client currentClient = null;

    /**
     * Константы запросов к БД
     */
    private static final String SQL_CLIENT_REMOVE_PET = "DELETE FROM pets WHERE client_id = ? AND name = ?";
    private static final String SQL_CLIENT_ADD_PET = "INSERT INTO pets (name, type_id, client_id) VALUES (?, ?, ?)";
    private static final String SQL_CLIENT_PETS_COUNT = "SELECT COUNT(*) FROM pets WHERE client_id = ?";
    private static final String SQL_CLIENT_PETS = "SELECT * FROM pets WHERE client_id = ?";
    private static final String SQL_ADD_CLIENT = "INSERT INTO clients (client_id, name) VALUES (?, ?)";
    private static final String SQL_IS_UNIQUE_CLIENT_ID = "SELECT * FROM clients WHERE client_id = ?";
    private static final String SQL_GET_ALL_CLIENTS = "SELECT * FROM clients";
    private static final String SQL_REMOVE_ALL_PETS_OF_CLIENT = "DELETE FROM pets WHERE client_id = ?";
    private static final String SQL_REMOVE_CLIENT = "DELETE FROM clients WHERE client_id = ?";
    private static final String SQL_RENAME_CLIENT = "UPDATE clients SET name = ? WHERE client_id = ?";
    private static final String SQL_CLIENT_ID_PART = "SELECT * FROM clients WHERE LOWER(client_id) LIKE LOWER(?)";
    private static final String SQL_CLIENT_ID_FULL = "SELECT * FROM clients WHERE client_id = ?";
    private static final String SQL_CLIENT_NAME_PART = "SELECT * FROM clients WHERE LOWER(name) LIKE LOWER(?)";
    private static final String SQL_CLIENT_NAME_FULL = "SELECT * FROM clients WHERE name = ?";
    private static final String SQL_CLIENT_PETS_NAME =
            "SELECT clients.client_id, clients.name \n" +
            "FROM clients INNER JOIN pets\n" +
            "ON clients.client_id = pets.client_id\n" +
            "WHERE pets.name = ?";

    /**
     * Конструктор
     */
    public JdbcClinic() {
        final Settings settings = Settings.getInstance();

        try {
            Class.forName(settings.getValue("jdbc.driver_class"));  // Не удалось настроить без этого
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(settings.getValue("jdbc.url"),
                    settings.getValue("jdbc.username"), settings.getValue("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    /**
     * Получить список клиентов клиники
     * Вместо реальных животных будут созданы "пустышки"! Т.к. метод используется для
     * отображения клиентов и только количества их животных.
     * @return Клиенты клиники
     */
    @Override
    public List<Client> getClients() {
        final List<Client> clients = new CopyOnWriteArrayList<>();

        try (final Statement statement = this.connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_CLIENTS)) {

            while (resultSet.next()) {
                Client clientTmp = new Client(resultSet.getString("name"), resultSet.getString("client_id"));
                fillClientWithEmptyPets(clientTmp);
                clients.add(clientTmp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    /**
     * Получить текущего клиента для редактирования
     * @return Текущий клиент
     */
    @Override
    public Client getCurrentClient() {
        return this.currentClient;
    }

    /**
     * Добавление клиента в БД
     * В таблицу добавляется только имя и id! Животные должны добавляться отдельно.
     * @param client Клиент
     */
    @Override
    public void addClient(final Client client) {
//        executeUpdateForPreparedStatement(SQL_ADD_CLIENT, client.getId(), client.getFullName());
        executePreparedStatement(SQL_ADD_CLIENT, new Command() {
            @Override
            public void execute(PreparedStatement statement) throws SQLException {
                statement.setString(1, client.getId());
                statement.setString(2, client.getFullName());
                statement.executeUpdate();
            }
        });
/*        try (final PreparedStatement statement = this.connection.prepareStatement(SQL_ADD_CLIENT)) {

            statement.setString(1, client.getId());
            statement.setString(2, client.getFullName());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Поиск клиентов по заданным критериям
     * @param type Тип поиска
     * @param toSearch Строка поиска
     * @return Список найденных клиентов
     */
    @Override
    public List<Client> findClients(final Client.SearchType type, final String toSearch) {
        final List<Client> found = new CopyOnWriteArrayList<>();
        String searchQuery = getQueryBySearchType(type);

        executePreparedStatement(searchQuery, new Command() {
            @Override
            public void execute(PreparedStatement statement) throws SQLException {
                statement.setString(1, getSearchString(type, toSearch));

                try (final ResultSet resultSet = statement.executeQuery()){
                    while (resultSet.next()) {
                        Client clientTmp = new Client(resultSet.getString("name"), resultSet.getString("client_id"));
                        fillClientWithEmptyPets(clientTmp);
                        found.add(clientTmp);
                    }
                }
            }
        });
/*        try (final PreparedStatement statement = this.connection.prepareStatement(searchQuery)) {

            statement.setString(1, getSearchString(type, toSearch));

            try (final ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Client clientTmp = new Client(resultSet.getString("name"), resultSet.getString("client_id"));
                    fillClientWithEmptyPets(clientTmp);
                    found.add(clientTmp);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        return found;
    }

    /**
     * Добавление животного текущему клиенту клиники
     * @param typeString Строковый тип животного
     * @param name Имя животного
     */
    @Override
    public void addPetToCurrentClient(final String typeString, final String name) {
        try {
            getCurrentClient().addPet(PetFactory.createPet(PetType.getPetTypeByString(typeString), name));

            executePreparedStatement(SQL_CLIENT_ADD_PET, new Command() {
                @Override
                public void execute(PreparedStatement statement) throws SQLException {
                    statement.setString(1, name);
                    statement.setInt(2, Integer.parseInt(typeString));
                    statement.setString(3, currentClient.getId());
                    statement.executeUpdate();
                }
            });
        } catch (IllegalArgumentException e) {
            // Only in case of adding pet with the same Name and Type - the same pet.
            // Do nothing in this case!
        }
/*        try (final PreparedStatement statement = this.connection.prepareStatement(SQL_CLIENT_ADD_PET)) {
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(typeString));
            statement.setString(3, this.currentClient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Удаление животного у текущего клиента
     * @param name Имя животного
     */
    @Override
    public void removePetFromCurrentClient(final String name) {
        getCurrentClient().removePetByName(name);
//        executeUpdateForPreparedStatement(SQL_CLIENT_REMOVE_PET, this.currentClient.getId(), name);
        executePreparedStatement(SQL_CLIENT_REMOVE_PET, new Command() {
            @Override
            public void execute(PreparedStatement statement) throws SQLException {
                statement.setString(1, currentClient.getId());
                statement.setString(2, name);
                statement.executeUpdate();
            }
        });
/*        try (final PreparedStatement statement = this.connection.prepareStatement(SQL_CLIENT_REMOVE_PET)) {
            statement.setString(1, this.currentClient.getId());
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Выбрать первого попавшегося клиента(удовлетворяющего параметрам поиска), как текущего
     * @param type Тип поиска
     * @param searchString Строка поиска
     */
    @Override
    public void selectFirstMatchingClient(Client.SearchType type, final String searchString) {
        this.currentClient = null;
        String searchQuery = getQueryBySearchType(type);

        executePreparedStatement(searchQuery, new Command() {
            @Override
            public void execute(PreparedStatement statement) throws SQLException {
                statement.setString(1, searchString);

                try (final ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        currentClient = new Client(resultSet.getString("name"), resultSet.getString("client_id"));
                        fillCurrentClientWithPets();
                    }
                }
            }
        });
/*        try (final PreparedStatement statement = this.connection.prepareStatement(searchQuery)) {

            statement.setString(1, searchString);

            try (final ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    this.currentClient = new Client(resultSet.getString("name"), resultSet.getString("client_id"));
                    fillCurrentClientWithPets();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * Удаление текущего клиента и всех его животных из БД
     */
    @Override
    public void removeCurrentClient() {
        removeCurrentClientPets();
//        executeUpdateForPreparedStatement(SQL_REMOVE_CLIENT, this.currentClient.getId());
        executePreparedStatement(SQL_REMOVE_CLIENT, new Command() {
            @Override
            public void execute(PreparedStatement statement) throws SQLException {
                statement.setString(1, currentClient.getId());
                statement.executeUpdate();
                currentClient = null;
            }
        });
/*        try (final PreparedStatement statement = this.connection.prepareStatement(SQL_REMOVE_CLIENT)) {
            statement.setString(1, this.currentClient.getId());
            statement.executeUpdate();
            this.currentClient = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
//        this.currentClient = null;
    }

    /**
     * Переименование текущего клиента
     * @param newName Новое имя
     */
    @Override
    public void renameCurrentClient(final String newName) {
//        executeUpdateForPreparedStatement(SQL_RENAME_CLIENT, newName, this.currentClient.getId());
        executePreparedStatement(SQL_RENAME_CLIENT, new Command() {
            @Override
            public void execute(PreparedStatement statement) throws SQLException {
                statement.setString(1, newName);
                statement.setString(2, currentClient.getId());
                statement.executeUpdate();
                currentClient.setFullName(newName);
            }
        });

/*        try (final PreparedStatement statement = this.connection.prepareStatement(SQL_RENAME_CLIENT)) {
            statement.setString(1, newName);
            statement.setString(2, this.currentClient.getId());
            statement.executeUpdate();
            this.currentClient.setFullName(newName);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

//        this.currentClient.setFullName(newName);
    }

    /**
     * Проверка уникальности Id клиента в таблице клиентов
     * @param clientId Id для проверки
     * @return Уникальность Id
     */
    @Override
    public boolean isUniqueClientId(final String clientId) {
        boolean uniqueId = true;

        try (final PreparedStatement statement = this.connection.prepareStatement(SQL_IS_UNIQUE_CLIENT_ID)) {

            statement.setString(1, clientId);

            try (final ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next())
                    uniqueId = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uniqueId;
    }

    /**
     * Закрытие соединения с БД.
     */
    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    interface Command {
        void execute(PreparedStatement statement) throws SQLException;
    }

    private void executePreparedStatement(String SqlQuery, Command command) {
        try (final PreparedStatement statement = this.connection.prepareStatement(SqlQuery)) {
            command.execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Выполнить update для указанного SQL-запроса
     * @param SqlQuery Строка для PreparedStatement
     * @param params Параметры PreparedStatement
     */
/*    private void executeUpdateForPreparedStatement(String SqlQuery, String... params) {
        try (final PreparedStatement statement = this.connection.prepareStatement(SqlQuery)) {
            for (int i = 1; i < params.length + 1; i++) {
                statement.setString(i, params[i - 1]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Заполнение клиента животными-пустышками.
     * Вместо считывания всех строк из таблицы животных, считываются только количества.
     * @param client Клиент для заполнения
     */
    private void fillClientWithEmptyPets(final Client client) {
        executePreparedStatement(SQL_CLIENT_PETS_COUNT, new Command() {
            @Override
            public void execute(PreparedStatement statement) throws SQLException {
                statement.setString(1, client.getId());

                try (final ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        int petsNumber = resultSet.getInt("count");
                        for (int i = 0; i < petsNumber; i++) {
                            client.addPet(new SomePet(Integer.toString(i)));
                        }
                    }
                }
            }
        });
/*        try (final PreparedStatement statement = this.connection.prepareStatement(SQL_CLIENT_PETS_COUNT)) {
            statement.setString(1, client.getId());

            try (final ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    int petsNumber = resultSet.getInt("count");
                    for (int i = 0; i < petsNumber; i++) {
                        client.addPet(new SomePet(Integer.toString(i)));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Получить строку поиска в зависимости от типа поиска
     * @param type Тип поиска
     * @param toSearch Входящая строка поиска
     * @return Результирующая строка поиска
     */
    private String getSearchString(Client.SearchType type, String toSearch) {
        String result = toSearch;

        if (hasLikeInSQL(type))
            result = "%" + toSearch + "%";

        return result;
    }

    /**
     * Проверка на использование ключевого слова LIKE в SQL-запросе
     * В этом случае строку поиска нужно окружать символом '%'
     * @param type Тип поиска
     * @return Наличие LIKE в запросе
     */
    private boolean hasLikeInSQL(Client.SearchType type) {
        return type == Client.SearchType.NAME_PART || type == Client.SearchType.ID_PART;
    }

    /**
     * Заполнение текущего клиента реальными животными.
     * Происходит считывание всех строк из таблицы животных, которые соответствуют клиенту.
     */
    private void fillCurrentClientWithPets() {
        if (this.currentClient != null) {
            executePreparedStatement(SQL_CLIENT_PETS, new Command() {
                @Override
                public void execute(PreparedStatement statement) throws SQLException {
                    statement.setString(1, currentClient.getId());

                    try (final ResultSet resultSet = statement.executeQuery()){
                        while (resultSet.next())
                            currentClient.addPet(PetFactory.createPet(
                                    PetType.getPetTypeByString(resultSet.getString("type_id")),
                                    resultSet.getString("name")));
                    }
                }
            });
/*            try (final PreparedStatement statement = this.connection.prepareStatement(SQL_CLIENT_PETS)) {
                statement.setString(1, this.currentClient.getId());

                try (final ResultSet resultSet = statement.executeQuery()){
                    while (resultSet.next())
                        this.currentClient.addPet(PetFactory.createPet(
                                PetType.getPetTypeByString(resultSet.getString("type_id")),
                                resultSet.getString("name")));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }
    }

    /**
     * Формирование SQL-запроса в зависимости от типа поиска
     * @param type Тип поиска клиентов
     * @return SQL-запрос
     */
    private String getQueryBySearchType(Client.SearchType type) {
        String searchQuery = "";

        switch (type) {
            case ID_PART:
                searchQuery = SQL_CLIENT_ID_PART;
                break;
            case ID_FULL:
                searchQuery = SQL_CLIENT_ID_FULL;
                break;
            case NAME_PART:
                searchQuery = SQL_CLIENT_NAME_PART;
                break;
            case NAME_FULL:
                searchQuery = SQL_CLIENT_NAME_FULL;
                break;
            case PETS_NAME:
                searchQuery = SQL_CLIENT_PETS_NAME;
                break;
        }

        return searchQuery;
    }

    /**
     * Удаление всех животных текущего клиента
     */
    private void removeCurrentClientPets() {
//        executeUpdateForPreparedStatement(SQL_REMOVE_ALL_PETS_OF_CLIENT, this.currentClient.getId());

        executePreparedStatement(SQL_REMOVE_ALL_PETS_OF_CLIENT, new Command() {
            @Override
            public void execute(PreparedStatement statement) throws SQLException {
                statement.setString(1, currentClient.getId());
                statement.executeUpdate();
            }
        });
/*
        try (final PreparedStatement statement = this.connection.prepareStatement(SQL_REMOVE_ALL_PETS_OF_CLIENT)) {
            statement.setString(1, this.currentClient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }

}
