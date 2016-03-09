import sys
from PyQt4 import QtCore, QtGui
from MainWindow import Ui_MainWindow
from EncryptMessage import EncryptDialog
from rsa import RSA

class MainDialog(QtGui.QDialog):
    def __init__(self, parent=None):
        # RSA
        self.rsa = RSA()

        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        self.ui.btnGenerate.clicked.connect(self.generateKeys)
        self.ui.btnEncMessage.clicked.connect(self.createEncryptMsgDialog)
        self.generateKeys()

    def generateKeys(self):
        self.rsa.genKeys()
        n,e = self.rsa.getPublicKey()
        self.ui.textPublicKey_n.setText(str(n))
        self.ui.textPublicKey_e.setText(str(e))
        self.ui.textPrivateKey.setText(str(self.rsa.getPrivateKey()))

    def createEncryptMsgDialog(self):
        n, e = self.rsa.getPublicKey()
        d = self.rsa.getPrivateKey()
        encryptDialog = EncryptDialog(self.rsa, self)
        encryptDialog.show()

if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    myapp = MainDialog()
    myapp.show()
    sys.exit(app.exec_())
