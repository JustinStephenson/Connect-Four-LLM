#!/bin/sh
# Pull a model into the running Ollama container if it doesn't already exist

MODEL="llama3.2"
echo "Checking if model '$MODEL' exists in Docker container..."

if docker exec ollama ollama list | grep -qw "$MODEL"; then
  echo "Model '$MODEL' already exists. Skipping pull."
else
  echo "Model '$MODEL' not found. Pulling..."
  docker exec -it ollama ollama pull "$MODEL"
fi