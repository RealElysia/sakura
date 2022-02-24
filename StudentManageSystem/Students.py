from flask import Flask, request, render_template, url_for, redirect, jsonify
from flask_sqlalchemy import SQLAlchemy
from StudentManageSystem.Teacher import Teachers

app = Flask(__name__)
app.config.from_object('config')
db = SQLAlchemy(app)


class Students(db.Model):
    __tablename__ = 'studentsMsg'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    gender = db.Column(db.String(5), nullable=False)
    name = db.Column(db.String(30), nullable=False, unique=True)
    score = db.Column(db.String(10), nullable=False)

    def __repr__(self):
        return '%d, %s, %s, %d' % (self.id, self.name, self.gender, self.score)

    def __init__(self, name, gender, score):
        self.name = name
        self.gender = gender
        self.score = score
        db.create_all()