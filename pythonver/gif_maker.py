import imageio
filenames = []
for i in range(0, 335):
    filenames.append(f'./imgs/solving{i}.png')
images = []
for filename in filenames:
    images.append(imageio.imread(filename))
imageio.mimsave('./resources/bfs_solving.gif', images)