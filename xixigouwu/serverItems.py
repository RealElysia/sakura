import pymysql
import json


class Items:
    """
    实现商品的入库、查询、修改、删除功能
    """

    def __init__(self):
        self.db = pymysql.connect(
            host='localhost',
            user='root',
            password='ms111111',
            port=3306,
            db='Items'
        )
        self.cursor = self.db.cursor()

    def save_item(self, id, brand, type, name, price, sales, inventory, pictureUrl):
        sql = ('insert into items (id, brand, type, name, price, sales, inventory, pictureUrl) '
               'values (%d,"%s","%s","%s",%d,%d,%d,"%s")'
               % (id, brand, type, name, price, sales, inventory, pictureUrl))

        self.cursor.execute(sql)
        self.db.commit()
        self.db.close()

    def query_all(self):
        sql = 'select * from items'
        result = {}
        try:
            self.cursor.execute(sql)
            data = self.cursor.fetchall()
            if data is not None:
                items = []
                for i in range(len(data)):
                    a = {
                        'id': data[i][0],
                        'brand': data[i][1],
                        'type': data[i][2],
                        'name': data[i][3],
                        'price': data[i][4],
                        'sales': data[i][5],
                        'inventory': data[i][6],
                        'pictureUrl': data[i][7]
                    }
                    items.append(a)
                result['items'] = items
                return result
            else:
                return '查询的商品不存在，请重新输入'
        except:
            print('查询异常')
        self.db.close()

    def queryByType(self, type):
        sql = f'select * from items where type="{type}"'
        result = {}
        try:
            self.cursor.execute(sql)
            data = self.cursor.fetchall()
            if data is not None:
                items = []
                for i in range(len(data)):
                    a = {
                        'id': data[i][0],
                        'brand': data[i][1],
                        'type': data[i][2],
                        'name': data[i][3],
                        'price': data[i][4],
                        'sales': data[i][5],
                        'inventory': data[i][6],
                        'pictureUrl': data[i][7]
                    }
                    items.append(a)
                result['items'] = items
                return result
            else:
                return '查询的商品不存在，请重新输入'
        except:
            print('查询异常')
        self.db.close()

    def queryByName(self, name):
        sql = f'select * from items where name like "%{name}%"'
        result = {}
        try:
            self.cursor.execute(sql)
            data = self.cursor.fetchall()
            if data is not None:
                items = []
                for i in range(len(data)):
                    a = {
                        'id': data[i][0],
                        'brand': data[i][1],
                        'type': data[i][2],
                        'name': data[i][3],
                        'price': data[i][4],
                        'sales': data[i][5],
                        'inventory': data[i][6],
                        'pictureUrl': data[i][7]
                    }
                    items.append(a)
                result['items'] = items
                return result
            else:
                return '查询的商品不存在，请重新输入'
        except:
            print('查询异常')
        self.db.close()

    def update_item(self, id, args, value):
        sql = 'update items set %s=%s where id=%d' % (args, value, id)
        try:
            self.cursor.execute(sql)
            self.db.commit()
        except:
            self.db.rollback()
        self.db.close()

    def delete_item(self, id):
        sql = 'delete from items where id=%d' % id
        try:
            self.cursor.execute(sql)
            self.db.commit()
        except:
            self.db.rollback()
        self.db.close()


if __name__ == '__main__':
    c = Items()
    print(c.queryByType('phone'))
