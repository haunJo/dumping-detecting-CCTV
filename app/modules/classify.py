import torch
from clip import clip
from PIL import Image
import numpy as np

class Classifier():
    def __init__(self):
        self.device = "cuda" if torch.cuda.is_available() else "cpu"
        print("CLIP : GPU available")
        self.model, self.preprocess = clip.load("ViT-B/32", device=self.device)
        
        # redis_client - rai.Client()
        
        # model_tensor = redis_client.tensorget("clip_model_weights")
        # model_script = ml2rt.rt2torch(model_tensor)
        # self.model = torch.jit.load(model_script)
        
        # self.preprocess = redis_client.get("clip_preprocess")
        
        self.labels = [
            'putting something', 
            'walking', 
            'throwing someting', 
            'dumping something', 
            'holding something'
        ]
        self.tokens = torch.cat([clip.tokenize(f"a photo of a person {c}") for c in self.labels]).to(self.device)
#         self.tokens = torch.cat([clip.tokenize(f"{c}") for c in self.labels]).to(self.device)

    def __del__(self):
        print("장시간 분류기를 사용하지 않아 메모리를 해제합니다")
        
        
    def classify(self, source: Image.Image) -> str:
        image = self.preprocess(source).unsqueeze(0).to(self.device)

        with torch.no_grad():
            image_features = self.model.encode_image(image)
            text_features = self.model.encode_text(self.tokens)

            logits_per_image, logits_per_text = self.model(image, self.tokens) 
            probs = logits_per_image.softmax(dim=-1).cpu().numpy()

        # Pick the top 5 most similar labels for the image
        # image_features /= image_features.norm(dim=-1, keepdim=True)
        # text_features /= text_features.norm(dim=-1, keepdim=True)
        # similarity = (100.0 * image_features @ text_features.T).softmax(dim=-1)
        # values, indices = similarity[0].topk(2)

#         # Print the result of top 5 most similar labels
#         s = ""
#         print("\nTop predictions:\n")
#         for value, index in zip(values, indices):
#             s += f"{self.labels[index]:>16s}: {100 * value.item():.2f}%\n"
#         print(s)
            
        # Return the most similar label's label and probability
        probs= probs*100
        probs = np.round(probs, decimals=2)
        return probs