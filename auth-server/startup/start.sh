#!/bin/bash
echo "starting idm_server"
nohup mvn spring-boot:run -Denv=ali & echo $! > ./startup/process.pid
echo "done!"
