import random

import requests, os, time
from multiprocessing import Pool

req = requests.session()


def get_url(user):
    since, count = [0], 0
    img_url = []
    while count <= 9:
        url = 'https://bcy.net/apiv3/user/selfPosts?uid=%s&since=%s' % (user, since[-1])
        resp = req.get(url).json()
        id = resp['data']['items'][-1]['since']
        since.append(id)
        ll = len(resp['data']['items'])  # 单次请求集长度
        for i in range(ll):
            il = len(resp['data']['items'][i]['item_detail']['image_list'])  # 单个图片集长度
            for j in range(il):
                pathImg = resp['data']['items'][i]['item_detail']['image_list'][j]['path']
                img_url.append(pathImg)
        count += 1
    return img_url


# 保存图片
def save_img(url):
    if not os.path.exists('金鱼COS'):
        os.makedirs('金鱼COS')
    img = req.get(url).content
    sx = random.randint(1, 999)
    with open('金鱼COS/' + '金鱼' + str(sx) + '.jpg', 'wb') as f:
        f.write(img)


if __name__ == '__main__':
    start = time.time()
    print('开始抓取URL，请稍等。。。')
    url = get_url(162710)
    print(url)
    end_1 = time.time()
    print('URL抓取完成，开始下载图片！', '时间：' + str(end_1-start))
    p = Pool(processes=30)
    p.map(save_img, url)
    p.close()
    p.join()
    end_2 = time.time()
    print('下载完成！', '时间：' + str(end_2-end_1))
