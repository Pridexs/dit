# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'EncryptMessage.ui'
#
# Created by: PyQt4 UI code generator 4.11.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui
from rsa import RSA

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    def _fromUtf8(s):
        return s

try:
    _encoding = QtGui.QApplication.UnicodeUTF8
    def _translate(context, text, disambig):
        return QtGui.QApplication.translate(context, text, disambig, _encoding)
except AttributeError:
    def _translate(context, text, disambig):
        return QtGui.QApplication.translate(context, text, disambig)

class Ui_EncryptDialog(object):
    def setupUi(self, Dialog):
        Dialog.setObjectName(_fromUtf8("Dialog"))
        Dialog.resize(360, 372)
        self.textEncryptedMessage = QtGui.QTextEdit(Dialog)
        self.textEncryptedMessage.setGeometry(QtCore.QRect(10, 180, 341, 121))
        self.textEncryptedMessage.setReadOnly(False)
        self.textEncryptedMessage.setObjectName(_fromUtf8("textEncryptedMessage"))
        self.textMessage = QtGui.QTextEdit(Dialog)
        self.textMessage.setGeometry(QtCore.QRect(10, 30, 341, 121))
        self.textMessage.setReadOnly(False)
        self.textMessage.setObjectName(_fromUtf8("textMessage"))
        self.btnDecrypt = QtGui.QPushButton(Dialog)
        self.btnDecrypt.setGeometry(QtCore.QRect(10, 340, 341, 23))
        self.btnDecrypt.setObjectName(_fromUtf8("btnDecrypt"))
        self.labelPRK = QtGui.QLabel(Dialog)
        self.labelPRK.setGeometry(QtCore.QRect(10, 160, 101, 16))
        self.labelPRK.setObjectName(_fromUtf8("labelPRK"))
        self.btnEncrypt = QtGui.QPushButton(Dialog)
        self.btnEncrypt.setGeometry(QtCore.QRect(10, 310, 341, 23))
        self.btnEncrypt.setObjectName(_fromUtf8("btnEncrypt"))
        self.labelBK = QtGui.QLabel(Dialog)
        self.labelBK.setGeometry(QtCore.QRect(10, 10, 61, 16))
        self.labelBK.setObjectName(_fromUtf8("labelBK"))

        self.retranslateUi(Dialog)
        QtCore.QMetaObject.connectSlotsByName(Dialog)

    def retranslateUi(self, Dialog):
        Dialog.setWindowTitle(_translate("Dialog", "Encrypt/Decrypt Message", None))
        self.btnDecrypt.setText(_translate("Dialog", "Decrypt", None))
        self.labelPRK.setText(_translate("Dialog", "Encrypted Message", None))
        self.btnEncrypt.setText(_translate("Dialog", "Encrypt", None))
        self.labelBK.setText(_translate("Dialog", "Message", None))

# Dialog Functions
class EncryptDialog(QtGui.QDialog):
    def __init__(self, rsa, parent=None):
        self.rsa = rsa

        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_EncryptDialog()
        self.ui.setupUi(self)
        self.ui.textMessage.setText("attack at dawn")
        self.ui.btnEncrypt.clicked.connect(self.encryptMessage)

    def encryptMessage(self):
        msg = self.ui.textMessage.toPlainText()
        n, e = self.rsa.getPublicKey()
        encryptedMsg = self.rsa.encrypt(msg, n, e)
        self.ui.textEncryptedMessage.setText("{}".format(encryptedMsg))
