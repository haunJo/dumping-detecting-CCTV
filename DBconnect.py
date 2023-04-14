import pymysql
import pandas as pd
import os
import base64
from PIL import Image
from io import BytesIO
conn = pymysql.connect(
    host='ec2-43-201-101-246.ap-northeast-2.compute.amazonaws.com',
    user='johaun', 
    password='8312', 
    db= 'SCV', 
    charset='utf8')

cur = conn.cursor(pymysql.cursors.DictCursor)

buffer = BytesIO()

work_dir = 'data'
dir_list = os.listdir(f'{work_dir}/images')
print(dir_list)
for images in dir_list[1:]:
    file_list = os.listdir(f'{work_dir}/images/{images}')
    for image in file_list:
        im = Image.open(f'{work_dir}/images/{images}/{image}')
        im = im.resize((400, 300))
        im.show()
        im.save(buffer, format='jpeg')
        img_str = base64.b64encode(buffer.getvalue())
        img_str = img_str.decode('UTF-8')
        print(img_str)
        print(image)
        sql = f"""insert into image (cctv_id, filename, image_data) values (1, '{image}', '{img_str}')"""
        cur.execute(sql)
        conn.commit()
conn.close()