import json
from flask import Flask, Blueprint
from flask import request, url_for
from flask import render_template, redirect
from datetime import datetime
from xixigouwu.users import Users
from xixigouwu.serverItems import Items

bp = Blueprint('sell', __name__)


@bp.route('/seller')
def sell_item():
    resp = {}
    if request.method == 'GET':
        resp = Items().query_all()
    return render_template('sellers.html', result=resp['items'])


@bp.route('/queryByName', methods=['POST'])
def queryByName():
    if request.method == 'POST':
        res = {'code': 0, 'msg': '查询成功'}
        q = request.form['param']
        resp = Items().queryByName(q)
        res['items'] = resp['items']
        return json.dumps(res)


@bp.route('/queryByType', methods=['GET'])
def queryByType():
    if request.method == 'GET':
        res = {'code': 0, 'msg': '查询成功'}
        r = {}
        q = request.args.get('type')
        if q == 'all':
            r = Items().query_all()
        else:
            r = Items().queryByType(q)
        res['items'] = r['items']
        return json.dumps(res)
