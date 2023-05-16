import json
import pymysql
import re

# 로컬 MariaDB
db = pymysql.connect(
    user='root',
    passwd='playdata',
    host='127.0.0.1',
    db='playGG',
    charset='utf8'
)

# AWS RDS
with open("src/main/resources/application-hide.properties") as f:
    properties = f.readlines()
    
db = pymysql.connect(
    user=f'{properties[1].split("=")[1][:-1]}',
    passwd=f'{properties[2].split("=")[1][:-1]}',
    host=f'{properties[3]}',
    db='playGG',
    charset='utf8'
)

cursor = db.cursor(pymysql.cursors.DictCursor)

with open("src/main/resources/json/championFull.json", encoding='utf-8') as f:
    json_object = json.load(f)
    for champ in json_object["data"] :
        for skill in json_object.get("data").get(champ).get("spells") :
            cursor.execute("INSERT INTO `skill_info` VALUES (%s, %s, %s, %s, %s, %s, %s)"
                            , [
                                skill.get("id"),
                                skill.get("name"),
                                skill.get("description"),
                                re.sub("[a-zA-Z<>/]","", skill.get("tooltip")),
                                '/'.join(str(e) for e in skill.get("cooldown")),
                                '/'.join(str(e) for e in skill.get("cost")),
                                f"https://opgg-static.akamaized.net/meta/images/lol/spell/{skill.get('id')}.png?"
                            ])
db.commit()