import json
import pymysql

# 로컬 MariaDB
db = pymysql.connect(
    user='root',
    passwd='playdata',
    host='127.0.0.1',
    db='playGG',
    charset='utf8'
)

# AWS RDS
# with open("src/main/resources/application-hide.properties") as f:
#     properties = f.readlines()
    
# db = pymysql.connect(
#     user=f'{properties[1].split("=")[1][:-1]}',
#     passwd=f'{properties[2].split("=")[1][:-1]}',
#     host=f'{properties[3]}',
#     db='playGG',
#     charset='utf8'
# )


cursor = db.cursor(pymysql.cursors.DictCursor)

with open("src/main/resources/json/championFull.json", encoding='utf-8') as f:
    json_object = json.load(f)
    for champ in json_object["data"] :
        cursor.execute("INSERT INTO `champion_info` VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"
                        ,[json_object["data"].get(champ)["key"]
                        ,json_object["data"].get(champ)["name"]
                        ,f"https://opgg-static.akamaized.net/meta/images/lol/champion/{json_object.get('data').get(champ).get('image').get('full')}?"
                        ,json_object["data"].get(champ).get("passive").get("name")
                        ,json_object["data"].get(champ).get("spells")[0].get("id")
                        ,json_object["data"].get(champ).get("spells")[1].get("id")
                        ,json_object["data"].get(champ).get("spells")[2].get("id")
                        ,json_object["data"].get(champ).get("spells")[3].get("id")])
db.commit()