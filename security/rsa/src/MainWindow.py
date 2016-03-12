# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'MainWindow.ui'
#
# Created by: PyQt4 UI code generator 4.11.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

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

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName(_fromUtf8("MainWindow"))
        MainWindow.resize(360, 361)
        MainWindow.setSizeGripEnabled(False)
        self.labelPK_e = QtGui.QLabel(MainWindow)
        self.labelPK_e.setGeometry(QtCore.QRect(10, 10, 71, 16))
        self.labelPK_e.setObjectName(_fromUtf8("labelPK_e"))
        self.textPublicKey_e = QtGui.QTextEdit(MainWindow)
        self.textPublicKey_e.setGeometry(QtCore.QRect(10, 30, 341, 51))
        self.textPublicKey_e.setReadOnly(False)
        self.textPublicKey_e.setObjectName(_fromUtf8("textPublicKey_e"))
        self.textPrivateKey = QtGui.QTextEdit(MainWindow)
        self.textPrivateKey.setGeometry(QtCore.QRect(10, 200, 341, 61))
        self.textPrivateKey.setReadOnly(False)
        self.textPrivateKey.setObjectName(_fromUtf8("textPrivateKey"))
        self.labelPRK = QtGui.QLabel(MainWindow)
        self.labelPRK.setGeometry(QtCore.QRect(10, 180, 61, 16))
        self.labelPRK.setObjectName(_fromUtf8("labelPRK"))
        self.btnGenerate = QtGui.QPushButton(MainWindow)
        self.btnGenerate.setGeometry(QtCore.QRect(10, 270, 341, 23))
        self.btnGenerate.setObjectName(_fromUtf8("btnGenerate"))
        self.btnEncMessage = QtGui.QPushButton(MainWindow)
        self.btnEncMessage.setGeometry(QtCore.QRect(10, 300, 341, 23))
        self.btnEncMessage.setObjectName(_fromUtf8("btnEncMessage"))
        self.btnEncFile = QtGui.QPushButton(MainWindow)
        self.btnEncFile.setGeometry(QtCore.QRect(10, 330, 341, 23))
        self.btnEncFile.setObjectName(_fromUtf8("btnEncFile"))
        self.textPublicKey_n = QtGui.QTextEdit(MainWindow)
        self.textPublicKey_n.setGeometry(QtCore.QRect(10, 110, 341, 61))
        self.textPublicKey_n.setReadOnly(False)
        self.textPublicKey_n.setObjectName(_fromUtf8("textPublicKey_n"))
        self.labelBK_n = QtGui.QLabel(MainWindow)
        self.labelBK_n.setGeometry(QtCore.QRect(10, 90, 71, 16))
        self.labelBK_n.setObjectName(_fromUtf8("labelBK_n"))

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(_translate("MainWindow", "RSA Assignment 1", None))
        self.labelPK_e.setText(_translate("MainWindow", "Public Key (e)", None))
        self.labelPRK.setText(_translate("MainWindow", "Private Key", None))
        self.btnGenerate.setText(_translate("MainWindow", "Generate Private / Public Key", None))
        self.btnEncMessage.setText(_translate("MainWindow", "Encrypt / Decrypt Message", None))
        self.btnEncFile.setText(_translate("MainWindow", "Encrypt / Decrypt File", None))
        self.labelBK_n.setText(_translate("MainWindow", "Public Key (n)", None))
