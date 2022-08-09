#!/bin/bash
path=`pwd`
git fetch origin
echo 当前目录${path}项目已更新
br=`git branch | grep "*"`
cur=${br/* /}
echo 当前分支为 ${cur}
git merge origin/${cur} ${cur}
echo 更新完成！