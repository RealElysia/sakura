import pymysql
from xixigouwu import serverItems


class Cart:
    """
    购物车商品的查询、修改、添加、删除功能
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
        self.item = serverItems.Items()

    def add_item(self, id, name, pers):
        price = self.item.query_items(id)[4]
        sy = self.item.query_items(id)[6]
        if sy > pers:
            sql = 'insert into cart(id, name, pers, price) values (%d,"%s",%d,%d)' % (id, name, pers, price)
            self.cursor.execute(sql)
            self.db.commit()
        else:
            return '商品库存不足，请调整'
        self.db.close()

    def query_item(self):
        sql = 'select * from cart'
        self.cursor.execute(sql)
        data = self.cursor.fetchall()

        self.db.close()
        return data

    def delete_item(self, id):
        sql = 'delete from cart where id=%d' % id
        try:
            self.cursor.execute(sql)
            self.db.commit()
        except:
            self.db.rollback()
        self.db.close()

    def update_pers(self, pers, id):
        sql = 'update cart set pers=%d where id=%d' % (pers, id)
        sy = self.item.query_items(id)[6]
        if sy > pers:
            self.cursor.execute(sql)
            self.db.commit()
        else:
            return '商品库存不足，请调整'
        self.db.close()