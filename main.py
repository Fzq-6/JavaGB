import pandas as pd 
import random

lst = ['robot'] * 10
lst += ['human'] * 10
random.shuffle(lst)

data = pd.DataFrame({'whoAmI': lst})
data.head

one_hot = pd.DataFrame({category: (data['whoAmI'] == category).astype(int) for category in data['whoAmI'].unique()})

print(one_hot.head())