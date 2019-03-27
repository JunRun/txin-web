#!/bin/bash
sh /opt/txin-web/shutdown.sh
dataStr=`date +%Y%m%d`
mv -f /opt/txin-web/txin-web.jar /opt/txin-web/backup/txin-web.jar.$dataStr
cp -f /opt/txin-web/deploy/txin-web.jar /opt/txin-web/
sh /opt/txin-web/startup.sh
