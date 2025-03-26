db = db.getSiblingDB('appdb');

db.createUser({
    user: "dbuser", pwd: "dbpassword", roles: [{
        role: "readWrite", db: "appdb"
    }]
});

db.createCollection("urlMapping", {
    validator: {
        $jsonSchema: {
            bsonType: "object", required: ["shortUrl", "originalUrl"], properties: {
                shortenUrl: {
                    bsonType: "string", description: "must be a string and is required"
                }, originalURL: {
                    bsonType: "string", description: "must be a string and is required"
                }
            }
        }
    }
});


db.urlMapping.createIndex({"shortUrl": 1}, {unique: true});
db.urlMapping.insert({"shortUrl": "hello", "originalUrl": "https://github.com/haydenpt"});