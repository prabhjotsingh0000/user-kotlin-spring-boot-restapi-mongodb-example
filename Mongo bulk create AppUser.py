import json
import random
import string
import uuid

def generate_random_string(length):
    """Generate a random string of given length"""
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for _ in range(length))

def generate_non_repeating_uuids(num_uuids):
    uuids = set()
    while len(uuids) < num_uuids:
        new_uuid = uuid.uuid4()
        if new_uuid not in uuids:
            uuids.add(new_uuid)
    return list(uuids)

def generate_mongo_commands(file_path, num_users):
    """Generate Mongo commands for creating random AppUsers"""
    users = []
    for i in range(1, num_users + 1):
        first_name = generate_random_string(5)
        last_name = generate_random_string(6)
        email = f"{first_name}.{last_name}@example.com"
        username = f"{first_name}{last_name}"
        password = generate_random_string(8)
        is_logged_in = random.choice([True, False])
        user = {
            "_id": i,
            "firstName": first_name,
            "lastName": last_name,
            "email": email,
            "username": username,
            "password": password,
            "isLoggedIn": is_logged_in
        }
        users.append(user)
    with open(file_path, 'w') as file:
        json.dump(users, file, indent=2)

if __name__ == '__main__':
    num_users = 1000000
    file_path = 'create_users_mongo_commands.json'
    generate_mongo_commands(file_path, num_users)
    print(f"Mongo commands generated and saved to {file_path}")
