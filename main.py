import time
import os

from data import ImageFromVideo
from data import ImageFromCam
from modules.detect import Detector
from modules.classify import Classifier
from modules.crop import crop_image

import torch
import cv2
#ImageFromVideo.get_image('data/video/2023032319.mov')

# Initialize a class instance


if __name__ == "__main__":
    human_detector = Detector()
dumping_classifier = Classifier()

start = time.time()
cnt = 1
path = "./data/images/2023_04_01_15"
for img_path in os.listdir(path):
    src = os.path.join(path, img_path)
    image = cv2.imread(src)
    with torch.no_grad():
        det = human_detector.detect(image)

    for i, (*xyxy, conf, cls) in enumerate(reversed(det)):
        # xyxy -> (left_top_x, left_top_y, right_bottom_x, right_bottom_y)
        if cls == 0: # only save an image of person
            crropedImage = crop_image(image, tuple(map(float, xyxy)))
            pred, prob = dumping_classifier.classify(crropedImage)
            if pred == "throwing away" and prob >= 76:
                print(cnt, pred, prob, img_path, xyxy)
                cnt += 1
                # code to save the image in DB
        
print(time.time() - start)
