# Crop an image based on a specific coordinate
import numpy as np
import cv2
from PIL import Image


def crop_image(source: np.ndarray, coordinates: tuple) -> Image.Image:
    image = cv2.cvtColor(source, cv2.COLOR_BGR2RGB)
    image = Image.fromarray(image)
    croppedImage = image.crop(coordinates)
    return croppedImage