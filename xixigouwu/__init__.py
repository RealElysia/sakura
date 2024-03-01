from flask import Flask
from xixigouwu import base, seller


def createApp():
    app = Flask(__name__, instance_relative_config=True)
    app.register_blueprint(base.bp)
    app.register_blueprint(seller.bp)

    app.run(debug=True)


if __name__ == '__main__':
    createApp()