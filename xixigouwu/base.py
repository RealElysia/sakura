import json
from flask import Flask, Blueprint
from flask import request, url_for
from flask import render_template, redirect
from datetime import datetime
from xixigouwu.users import Users

bp = Blueprint('base', __name__)


@bp.route('/login')
def login():
    return render_template('login.html')


@bp.route('/userCheck', methods=['POST'])
def userCheck():
    if request.method == 'POST':
        id = int(request.form['id'])
        user = request.form['user']
        pwd = request.form['pwd']
        seller = request.form['seller']
        uid = Users().queryUser(id)
        res = {'code': 0, 'msg': '', 'success': True}
        if uid is None:
            res['code'] = 1
            res['msg'] = '此账号为空，请重试或注册！'
            return res
        if user != uid[1]:
            res['code'] = 1
            res['msg'] = '用户名错误，请重试！'
            return res
        if pwd != uid[2]:
            res['code'] = 1
            res['msg'] = '密码错误，请重试！'
            return res
        if seller == 0:
            return redirect(url_for('/seller'))
        else:
            return redirect(url_for('/buyer'))


@bp.route('/register')
def register():
    return render_template('register.html')


@bp.route('/addUser', methods=['POST'])
def addUser():
    if request.method == 'POST':
        user = request.form['user']
        pwd = request.form['pwd']

        seller = request.form['seller']
        if seller is None or seller == '':
            seller = 0
        else:
            seller = 1
        id = int(datetime.now().time().strftime('%H%M%S'))

        Users().addUser(id, user, pwd, seller)
        result = {'code': 0, 'msg': '注册成功', 'id': id}
        return json.dumps(result)