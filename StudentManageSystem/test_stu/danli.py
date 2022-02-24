import requests


class Danli(object):
    a = None
    b = False

    def __new__(cls):
        if not cls.a:
            cls.a = object.__new__(cls)
        return cls.a

    def __init__(self):
        if not self.b:
            self.req = requests.session()
            self.b = True