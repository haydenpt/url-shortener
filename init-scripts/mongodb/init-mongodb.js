db = db.getSiblingDB('appdb');

db.createUser({
    user: "dbuser",
    pwd: "dbpassword",
    roles: [{
        role: "readWrite",
        db: "appdb"
    }]
});

db.createCollection('urls');

db.urls.createIndex({ "url": 1 }, { unique: true });