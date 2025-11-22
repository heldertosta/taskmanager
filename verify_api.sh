#!/bin/bash

BASE_URL="http://localhost:8080/api/v1/tarefas"

echo "Starting API Verification..."

# 1. Create Task (Success)
echo "1. Creating Task (Success)..."
RESPONSE=$(curl -s -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"title": "Task 1", "description": "Description 1"}')
echo "Response: $RESPONSE"
TASK_ID=$(echo $RESPONSE | grep -o '"id":"[^"]*"' | cut -d'"' -f4)
echo "Created Task ID: $TASK_ID"

if [ -z "$TASK_ID" ]; then
  echo "Failed to create task. Exiting."
  exit 1
fi

# 2. Create Task (Validation Error)
echo -e "\n2. Creating Task (Validation Error)..."
curl -s -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{"title": "", "description": ""}'
echo ""

# 3. Get All Tasks
echo -e "\n3. Getting All Tasks..."
curl -s -X GET $BASE_URL

# 4. Get Task By ID
echo -e "\n\n4. Getting Task By ID..."
curl -s -X GET $BASE_URL/$TASK_ID

# 5. Update Task
echo -e "\n\n5. Updating Task..."
curl -s -X PUT $BASE_URL/$TASK_ID \
  -H "Content-Type: application/json" \
  -d '{"title": "Task 1 Updated", "description": "Description 1 Updated"}'

# 6. Delete Task
echo -e "\n\n6. Deleting Task..."
curl -s -X DELETE $BASE_URL/$TASK_ID

echo -e "\n\nVerification Complete."
