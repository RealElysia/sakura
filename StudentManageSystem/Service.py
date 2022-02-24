from flask import request, render_template, url_for, redirect, Flask
from flask_sqlalchemy import SQLAlchemy
from StudentManageSystem.Teacher import Teachers
from StudentManageSystem.Students import Students

app = Flask(__name__)
app.config.from_object('config')
db = SQLAlchemy(app)


@app.route('/', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        a = request.form.get('account')
        c = Teachers.query.filter_by(account=a).all()
        if c:
            return redirect(url_for('add_stu'))
        else:
            return 'user is not exists'
    return render_template('login.html')


@app.route('/register', methods=['GET', 'POST'])
def Register():
    if request.method == 'POST':
        a = request.form.get('account')
        b = request.form.get('password')
        c = Teachers.query.filter_by(account=a).all()
        if c:
            return '账号已存在，请返回登录！'
        else:
            d = Teachers(a, b)
            db.session.add(d)
            db.session.commit()
        return redirect(url_for('login'))
    return render_template('register.html')


@app.route('/index/add', methods=['GET', 'POST'])
def add_stu():  # 添加学生
    if request.method == 'POST':
        # 获取前端传递的信息
        name = request.form.get('name')
        gender = request.form.get('gender')
        score = request.form.get('score')
        res = Students.query.filter_by(name=name).all()
        if res:  # 判断学生是否存在
            return '该学生已存在！'
        else:
            data = Students(name, gender, score)  # 存入数据库
            db.session.add(data)
            db.session.commit()
    return render_template('add.html')


@app.route('/index/del', methods=['GET', 'POST'])
def del_stu():  # 删除学生
    if request.method == 'POST':
        del_name = request.form.get('name')  # 获取前端传递的信息
        res = Students.query.filter_by(name=del_name).first()  # 查询信息
        db.session.delete(res)  # 删除
        db.session.commit()
    return render_template('del.html')


@app.route('/index/change', methods=['GET', 'POST'])
def change():  # 修改信息
    if request.method == 'POST':
        name = request.form.get('name')  # 获取前端传递的信息
        a = Students.query.filter_by(name=name).first()  # 查询信息
        new_id = request.form.get('new_id')
        # 信息为空则不修改
        if new_id != '':
            a.id = new_id
        new_name = request.form.get('new_name')
        if new_name != '':
            a.name = new_name
        new_gender = request.form.get('new_gender')
        if new_gender != '':
            a.gender = new_gender
        new_score = request.form.get('new_score')
        if new_score != '':
            a.score = new_score
        db.session.commit()
    return render_template('change.html')


@app.route('/index/query', methods=['GET', 'POST'])
def query():  # 查询单个学生
    list_stu = []
    if request.method == 'POST':
        name = request.form.get('name')  # 获取前端传递的信息
        res = Students.query.filter_by(name=name).first()  # 查询信息
        list_stu.append(res)  # 加入列表
    return render_template('query.html', res=list_stu)  # 返回前端


@app.route('/index/queryAll', methods=['GET', 'POST'])
def queryAll():  # 查询所有
    list_stu = []
    if request.method == 'POST':
        i = 1
        a = Students.query.all()
        # 循环查询所有信息，追加列表
        while i < len(a) + 1:
            r = Students.query.filter_by(id=i).first()
            list_stu.append(r)
            i += 1
    return render_template('queryAll.html', result=list_stu)  # 返回前端


if __name__ == '__main__':
    app.run(debug=True)
