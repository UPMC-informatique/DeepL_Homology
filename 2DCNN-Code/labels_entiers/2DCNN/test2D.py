from __future__ import absolute_import
from __future__ import division
from __future__ import print_function
from datasets import *

# Imports
import numpy as np
import tensorflow as tf
import random

tf.logging.set_verbosity(tf.logging.INFO)

# Our application logic will be added here


def cnn_model_fn(features, labels, mode):
  """Model function for CNN."""
  # Input Layer
  input_layer = tf.reshape(features["x"], [-1, MAX, 45, 1])

  # Convolutional Layer #1
  conv1 = tf.layers.conv2d(
      inputs=input_layer,
      filters=10,
      strides=[1,1],
      kernel_size=[10, 10],
      padding="same",
      activation=tf.nn.relu)

  print(conv1)

  # Convolutional Layer #2
  conv2 = tf.layers.conv2d(
      inputs=conv1,
      filters=10,
      kernel_size=[10, 10],
      strides=[1,1],
      padding="same",
      activation=tf.nn.relu)

  # Convolutional Layer #3
  conv3 = tf.layers.conv2d(
      inputs=conv2,
      filters=10,
      kernel_size=[10, 10],
      strides=[1,1],
      padding="same",
      activation=tf.nn.relu)

  # Convolutional Layer #4
  conv4 = tf.layers.conv2d(
      inputs=conv3,
      filters=10,
      kernel_size=[10, 10],
      strides=[1,1],
      padding="same",
      activation=tf.nn.relu)

  # Convolutional Layer #5
  conv5 = tf.layers.conv2d(
      inputs=conv4,
      filters=10,
      kernel_size=[10, 10],
      strides=[1,1],
      padding="same",
      activation=tf.nn.relu)

  #pool layer #1
  pool = tf.layers.max_pooling2d(inputs=conv5, pool_size=[2, 2], strides=2)
  print(pool)

  #pool layer #2
  pool2 = tf.layers.max_pooling2d(inputs=pool, pool_size=[2, 2], strides=2)
  print(pool2)
  
  # Dense Layer
  pool2_flat = tf.reshape(pool2, [-1, 50 * 11 * 10])
  dense = tf.layers.dense(inputs=pool2_flat, units=5000, activation=tf.nn.relu)
  dropout = tf.layers.dropout(inputs=dense, rate=0.4, training=mode == tf.estimator.ModeKeys.TRAIN)

  print(dropout)
  
  # Logits Layer
  logits = tf.layers.dense(inputs=dropout, units=3901)

  predictions = { \
      # Generate predictions (for PREDICT and EVAL mode)
      "classes": tf.argmax(input=logits, axis=1), \
      # Add `softmax_tensor` to the graph. It is used for PREDICT and by the
      # `logging_hook`.
      "probabilities": tf.nn.softmax(logits, name="softmax_tensor")\
  }

  if mode == tf.estimator.ModeKeys.PREDICT:
    return tf.estimator.EstimatorSpec(mode=mode, predictions=predictions)

  # Calculate Loss (for both TRAIN and EVAL modes)
  loss = tf.losses.sparse_softmax_cross_entropy(labels=labels, logits=logits)

  # Configure the Training Op (for TRAIN mode)
  if mode == tf.estimator.ModeKeys.TRAIN:
    optimizer = tf.train.GradientDescentOptimizer(learning_rate=0.001)
    train_op = optimizer.minimize(loss=loss,global_step=tf.train.get_global_step())
    return tf.estimator.EstimatorSpec(mode=mode, loss=loss, train_op=train_op)

  # Add evaluation metrics (for EVAL mode)
  eval_metric_ops = {"accuracy": tf.metrics.accuracy(labels=labels, predictions=predictions["classes"])}
  
  return tf.estimator.EstimatorSpec(mode=mode, loss=loss, eval_metric_ops=eval_metric_ops)


# cette fonction va charger les donnees depuis les fichiers et les mettre sous forme de numpy array
# et ensuite les stocker dans de fichiers binaires afin de ne pas devoir les recharger entierment a
#chaque lancement du programme ce qui prend enormement du temps
def data_save():
  train_data, train_labels = dataset_construction("train_dataset.list_resf", "datasets/train_dataset.list.labels_entiers")
  train_data,train_labels = drop(train_data, train_labels, 0.5)
  print("taille de train = "+ str(len(train_data))+"  "+str(len(train_labels)))
  eval_data, eval_labels = dataset_construction("test_dataset.list_family_resf", "datasets/test_dataset.list_family.labels_entiers")
  print("taille de train = "+ str(len(eval_data))+"  "+str(len(eval_labels)))
  np.save("train_data", train_data)
  np.save("train_labels", train_labels)
  np.save("eval_data", eval_data)
  np.save("eval_labels", eval_labels)


def drop(dataset, labelset,rate):
  d = []
  l = []

  for i in range(len(dataset)):
    if (random.random() <= rate):
      d.append(dataset[i])
      l.append(labelset[i])

  return d,l

def taille():
  train_data = np.load("train_data.npy")
  train_labels = np.load("train_labels.npy")
  print("TAILLE train= "+ str(len(train_data)))
  print("TAILLE train labels= "+ str(len(train_labels)))
  eval_data = np.load("eval_data.npy")
  eval_labels = np.load("eval_labels.npy")
  print("TAILLE eval= "+ str(len(eval_data)))
  print("TAILLE eval labels= "+ str(len(eval_labels)))

def main(unused_argv):
  # Load training and eval data
  #on charge les donnees depuis les fichiers .npy construits a l'aide de la fonction data_save afin de ne pas devoir
  #les recharger entierement a chaque lancement
  train_data = np.load("train_data.npy")
  train_labels = np.load("train_labels.npy")
  print("TAILLE = "+ str(len(train_data)))
  eval_data = np.load("eval_data.npy")
  eval_labels = np.load("eval_labels.npy")

  train_data = train_data.astype(dtype= np.float32)
  eval_data = eval_data.astype(dtype= np.float32)

  # Create the Estimator
  mnist_classifier = tf.estimator.Estimator(model_fn=cnn_model_fn, model_dir="./")

  # Set up logging for predictions
  tensors_to_log = {"probabilities": "softmax_tensor"}
  logging_hook = tf.train.LoggingTensorHook(tensors=tensors_to_log, every_n_iter=50)

  # Train the model
  train_input_fn = tf.estimator.inputs.numpy_input_fn(\
    x={"x": train_data},\
    y=train_labels,\
    batch_size=10,\
    num_epochs=None,\
    shuffle=True)
  
  mnist_classifier.train(input_fn=train_input_fn,steps=10000,hooks=[logging_hook])

  # Evaluate the model and print results
  eval_input_fn = tf.estimator.inputs.numpy_input_fn(\
    x={"x": eval_data},\
    y=eval_labels,\
    num_epochs=1,\
    shuffle=False)
  
  eval_results = mnist_classifier.evaluate(input_fn=eval_input_fn)
  print(eval_results)




if __name__ == "__main__":
  tf.app.run()

