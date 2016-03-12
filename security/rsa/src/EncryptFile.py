# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'EncryptFile.ui'
#
# Created by: PyQt4 UI code generator 4.11.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import *
import ast

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

class Ui_EncryptFile(object):
    def setupUi(self, EncryptFile):
        EncryptFile.setObjectName(_fromUtf8("EncryptFile"))
        EncryptFile.resize(400, 314)
        self.label = QtGui.QLabel(EncryptFile)
        self.label.setGeometry(QtCore.QRect(10, 10, 41, 21))
        self.label.setObjectName(_fromUtf8("label"))
        self.label_2 = QtGui.QLabel(EncryptFile)
        self.label_2.setGeometry(QtCore.QRect(10, 30, 81, 31))
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.textFile1 = QtGui.QTextEdit(EncryptFile)
        self.textFile1.setGeometry(QtCore.QRect(100, 30, 221, 31))
        self.textFile1.setObjectName(_fromUtf8("textFile1"))
        self.btnOpen1 = QtGui.QPushButton(EncryptFile)
        self.btnOpen1.setGeometry(QtCore.QRect(330, 30, 61, 31))
        self.btnOpen1.setObjectName(_fromUtf8("btnOpen1"))
        self.btnSave_1 = QtGui.QPushButton(EncryptFile)
        self.btnSave_1.setGeometry(QtCore.QRect(330, 70, 61, 31))
        self.btnSave_1.setObjectName(_fromUtf8("btnSave_1"))
        self.label_3 = QtGui.QLabel(EncryptFile)
        self.label_3.setGeometry(QtCore.QRect(10, 70, 81, 31))
        self.label_3.setObjectName(_fromUtf8("label_3"))
        self.textFile2 = QtGui.QTextEdit(EncryptFile)
        self.textFile2.setGeometry(QtCore.QRect(100, 70, 221, 31))
        self.textFile2.setObjectName(_fromUtf8("textFile2"))
        self.btnEncrypt = QtGui.QPushButton(EncryptFile)
        self.btnEncrypt.setGeometry(QtCore.QRect(330, 110, 61, 31))
        self.btnEncrypt.setObjectName(_fromUtf8("btnEncrypt"))
        self.line = QtGui.QFrame(EncryptFile)
        self.line.setGeometry(QtCore.QRect(0, 150, 401, 16))
        self.line.setFrameShadow(QtGui.QFrame.Plain)
        self.line.setLineWidth(3)
        self.line.setFrameShape(QtGui.QFrame.HLine)
        self.line.setObjectName(_fromUtf8("line"))
        self.btnOpen2 = QtGui.QPushButton(EncryptFile)
        self.btnOpen2.setGeometry(QtCore.QRect(330, 190, 61, 31))
        self.btnOpen2.setObjectName(_fromUtf8("btnOpen2"))
        self.btnSave2 = QtGui.QPushButton(EncryptFile)
        self.btnSave2.setGeometry(QtCore.QRect(330, 230, 61, 31))
        self.btnSave2.setObjectName(_fromUtf8("btnSave2"))
        self.txtFile4 = QtGui.QTextEdit(EncryptFile)
        self.txtFile4.setGeometry(QtCore.QRect(100, 230, 221, 31))
        self.txtFile4.setObjectName(_fromUtf8("txtFile4"))
        self.label_4 = QtGui.QLabel(EncryptFile)
        self.label_4.setGeometry(QtCore.QRect(10, 170, 41, 21))
        self.label_4.setObjectName(_fromUtf8("label_4"))
        self.label_5 = QtGui.QLabel(EncryptFile)
        self.label_5.setGeometry(QtCore.QRect(10, 190, 81, 31))
        self.label_5.setObjectName(_fromUtf8("label_5"))
        self.label_6 = QtGui.QLabel(EncryptFile)
        self.label_6.setGeometry(QtCore.QRect(10, 230, 81, 31))
        self.label_6.setObjectName(_fromUtf8("label_6"))
        self.textFile3 = QtGui.QTextEdit(EncryptFile)
        self.textFile3.setGeometry(QtCore.QRect(100, 190, 221, 31))
        self.textFile3.setObjectName(_fromUtf8("textFile3"))
        self.btnDecrypt = QtGui.QPushButton(EncryptFile)
        self.btnDecrypt.setGeometry(QtCore.QRect(330, 270, 61, 31))
        self.btnDecrypt.setObjectName(_fromUtf8("btnDecrypt"))

        self.retranslateUi(EncryptFile)
        QtCore.QMetaObject.connectSlotsByName(EncryptFile)

    def retranslateUi(self, EncryptFile):
        EncryptFile.setWindowTitle(_translate("EncryptFile", "Encrypt / Decrypt File", None))
        self.label.setText(_translate("EncryptFile", "Encrypt:", None))
        self.label_2.setText(_translate("EncryptFile", "File:", None))
        self.btnOpen1.setText(_translate("EncryptFile", "Open", None))
        self.btnSave_1.setText(_translate("EncryptFile", "Save", None))
        self.label_3.setText(_translate("EncryptFile", "Save To", None))
        self.btnEncrypt.setText(_translate("EncryptFile", "Encrypt", None))
        self.btnOpen2.setText(_translate("EncryptFile", "Open", None))
        self.btnSave2.setText(_translate("EncryptFile", "Save", None))
        self.label_4.setText(_translate("EncryptFile", "Decrypt", None))
        self.label_5.setText(_translate("EncryptFile", "Encrypted File:", None))
        self.label_6.setText(_translate("EncryptFile", "Save To", None))
        self.btnDecrypt.setText(_translate("EncryptFile", "Decrypt", None))

# Dialog Functions
class EncryptFileDialog(QtGui.QDialog):
    def __init__(self, rsa, parent=None):
        self.rsa = rsa

        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_EncryptFile()
        self.ui.setupUi(self)
        self.ui.btnOpen1.clicked.connect(self.selectFile1)
        self.ui.btnSave_1.clicked.connect(self.selectFile2)
        self.ui.btnOpen2.clicked.connect(self.selectFile3)
        self.ui.btnSave2.clicked.connect(self.selectFile4)
        self.ui.btnEncrypt.clicked.connect(self.encryptFile)
        self.ui.btnDecrypt.clicked.connect(self.decryptFile)

    def encryptFile(self):
        ofilepath = self.ui.textFile1.toPlainText()
        sfilepath = self.ui.textFile2.toPlainText()
        of = open(ofilepath, 'r')
        sf = open(sfilepath, 'w')
        msg = of.read()
        n, e = self.rsa.getPublicKey()
        encryptedMsg = self.rsa.encrypt(msg, n, e)
        sf.write('{}'.format(encryptedMsg))

    def decryptFile(self):
        ofilepath = self.ui.textFile3.toPlainText()
        sfilepath = self.ui.txtFile4.toPlainText()
        of = open(ofilepath, 'r')
        sf = open(sfilepath, 'w')
        encryptedMsg = of.read()
        cipher = ast.literal_eval(encryptedMsg)
        n, e = self.rsa.getPublicKey()
        d = self.rsa.getPrivateKey()
        msg = self.rsa.decrypt(cipher, n, d)
        sf.write(str(msg))

    def selectFile1(self):
        self.ui.textFile1.setText(QtGui.QFileDialog.getOpenFileName(self))

    def selectFile2(self):
        self.ui.textFile2.setText(QtGui.QFileDialog.getSaveFileName(self))

    def selectFile3(self):
        self.ui.textFile3.setText(QtGui.QFileDialog.getOpenFileName(self))

    def selectFile4(self):
        self.ui.txtFile4.setText(QtGui.QFileDialog.getSaveFileName(self))
