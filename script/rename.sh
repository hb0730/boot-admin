#!/bin/bash
# 替换组织com.hb0730 名称
if [ $# -ne 1 ]; then
  echo "Usage: $0 <new_package_name>"
  exit 1
fi

NEW_PACKAGE_NAME=$1

# 替换包名
find . -type f -name "*.java" -exec sed -i "s/com\.hb0730/${NEW_PACKAGE_NAME}/g" {} +
find . -type f -name "*.xml" -exec sed -i "s/com\.hb0730/${NEW_PACKAGE_NAME}/g" {} +
