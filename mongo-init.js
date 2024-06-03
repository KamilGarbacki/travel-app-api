db.createUser(
    {
        user: "user1",
        pwd: "password",
        roles: [ { role: "readWrite", db: "db1" } ]
    }
)