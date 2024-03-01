import pymysql


class Users:
    def __init__(self):
        self.db = pymysql.connect(
            host='localhost',
            user='root',
            password='ms111111',
            port=3306,
            db='Items'
        )
        self.cursor = self.db.cursor()

    def addUser(self, id, user, pwd, seller):
        sql = ('insert into users (id, user, pwd, seller) values (%d,"%s","%s",%d)'
               % (id, user, pwd, seller))

        self.cursor.execute(sql)
        self.db.commit()
        self.db.close()

    def queryUser(self, id):
        sql = 'select * from users where id=%d' % id
        self.cursor.execute(sql)
        data = self.cursor.fetchone()
        self.db.commit()
        self.db.close()
        return data

    def delete_user(self, id):
        sql = 'delete from users where id=%d' % id

        self.cursor.execute(sql)
        self.db.commit()
        self.db.close()


if __name__ == '__main__':
    print(Users().queryUser(155441))
