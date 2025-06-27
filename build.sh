#!/bin/bash
cd frontend
npm install
npm run build
cp -r dist/* ../backend/src/main/resources/static/
cd ../backend
mvn clean package