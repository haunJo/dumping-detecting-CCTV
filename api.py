from fastapi import FastAPI, File, UploadFile
from fastapi.responses import JSONResponse
import cv2
import torch
import time
import os
import base64
import numpy as np
from io import BytesIO
from PIL import Image

from data import ImageFromVideo
from data import ImageFromCam
from modules.detect import Detector
from modules.classify import Classifier
from modules.crop import crop_image

import torch


app = FastAPI()

@app.post("/files/")
async def create_file(file : Request):
    content = await file.json()
    human_detector = Detector()
    dumping_classifier = Classifier()
    img_data = base64.b64decode(content["image"])
    pil_image = Image.open(BytesIO(img_data))
    img = cv2.imdecode(np.array(pil_image), cv2.COLOR_RGB2BGR)
    with torch.no_grad():
        det = human_detector.detect(img)
    for i, (*xyxy, conf, cls) in enumerate(reversed(det)):
        # xyxy -> (left_top_x, left_top_y, right_bottom_x, right_bottom_y)
        if cls == 0: # only save an image of person
            crropedImage = crop_image(img, tuple(map(float, xyxy)))
            pred, prob = dumping_classifier.classify(crropedImage)
            if pred == "throwing away" and prob >= 76:
                print(cnt, pred, prob, xyxy)
                cnt += 1
                # code to save the image in DB
    return JSONResponse({"filename" : content["filename"]})

@app.get("/")
async def root():
    return {"message" : "hello world"}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port = 8000)