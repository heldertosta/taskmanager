#!/bin/bash

BASE_URL="http://localhost:8090/tasks"

echo "1. Creating a new task..."
CREATE_RESPONSE=$(curl -s -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"title": "Test Task", "description": "This is a test task"}')
echo "Response: $CREATE_RESPONSE"

TASK_ID=$(echo $CREATE_RESPONSE | grep -o '"id":"[^"]*"' | cut -d'"' -f4)
echo "Created Task ID: $TASK_ID"

if [ -z "$TASK_ID" ]; then
  echo "Failed to create task. Exiting."
  exit 1
fi

echo -e "\n2. Listing all tasks..."
curl -s -X GET $BASE_URL | grep --color=auto "$TASK_ID"

echo -e "\n3. Getting task by ID..."
curl -s -X GET "$BASE_URL/$TASK_ID"

echo -e "\n4. Updating task..."
curl -s -X PUT "$BASE_URL/$TASK_ID" \
  -H "Content-Type: application/json" \
  -d '{"title": "Updated Task", "description": "This is an updated description"}'

echo -e "\n5. Verifying update..."
curl -s -X GET "$BASE_URL/$TASK_ID"

echo -e "\n6. Deleting task..."
curl -s -X DELETE "$BASE_URL/$TASK_ID"

echo -e "\n7. Verifying deletion (should be 404)..."
curl -s -w "%{http_code}\n" -X GET "$BASE_URL/$TASK_ID"
