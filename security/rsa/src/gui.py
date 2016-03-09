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
        self.ui.textPublicKey_n.textChanged.connect(self.onPublicKeyChanged)
        self.ui.textPublicKey_e.textChanged.connect(self.onPublicKeyChanged)
        self.ui.textPrivateKey.textChanged.connect(self.onPrivateKeyChanged)

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

    def onPublicKeyChanged(self):
        try:
            n = int(self.ui.textPublicKey_n.toPlainText())
            e = int(self.ui.textPublicKey_e.toPlainText())
            self.rsa.setPublicKey(n, e)
        except:
            print('Error.')

    def onPrivateKeyChanged(self):
        try:
            d = int(self.ui.textPrivateKey.toPlainText())
            self.rsa.setPrivateKey(d)
        except:
            print('Invalid Key')

if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    myapp = MainDialog()
    myapp.show()
    sys.exit(app.exec_())
