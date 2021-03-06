#!/bin/bash

PIDFILE="./startup/process.pid"  # PID文件路径
echo "stopping idm_server" 
if [ -f $PIDFILE ]; then
    echo "pid file exists...." 
    PID=$(cat $PIDFILE) 
    echo $PID          # 将PID从文件中读取，并作为一个变量
    sudo kill -9 $PID
    echo "done!"
fi