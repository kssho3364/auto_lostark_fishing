import numpy as np

import tensorflow as tf
assert tf.__version__.startswith('2')

from tflite_model_maker import model_spec
from tflite_model_maker import image_classifier
from tflite_model_maker.image_classifier import DataLoader

# image_path = tf.keras.utils.get_file(
#       'flower_photos',
#       'https://storage.googleapis.com/download.tensorflow.org/example_images/flower_photos.tgz',
#       untar=True)
image_path = 'D:/trainingImages'

data = DataLoader.from_folder(image_path)
train_data, test_data = data.split(0.9)

model = image_classifier.create(train_data)

loss, accuracy = model.evaluate(test_data)

model.export(export_dir='.', with_metadata=False)