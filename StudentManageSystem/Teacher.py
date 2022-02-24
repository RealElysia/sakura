from flask import Flask, request, render_template, url_for, redirect
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config.from_object('config')
db = SQLAlchemy(app)


class Teachers(db.Model):
    __tablename__ = 'teachersMsg'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    account = db.Column(db.Integer, nullable=False, unique=True)
    password = db.Column(db.String(15), nullable=False, unique=True)

    def __repr__(self):
        return '%d, %d, %s' % (self.id, self.account, self.password)

    def __init__(self, account, password):
        self.account = account
        self.password = password
        db.create_all()

