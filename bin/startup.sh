#!/bin/bash
nohup java -jar /opt/txin-web/txin-web.jar --spring.profiles.active=pro &
tail -f /opt/txin-web/nohup.out
