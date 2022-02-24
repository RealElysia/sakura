from StudentManageSystem import Service, Teacher
from flask import Flask, Blueprint

bp = Blueprint('app', __name__, template_folder='templates', url_prefix='index')

