# prankEmailRobot
client application to communicate with a SMTP server that automatically plays pranks on a list of victims
The current repository is the outcome of an educational assihnment proposed by Professor Liechti (http://iict.heig-vd.ch/team/show/20/liechti-olivier/ ).
The purpose of this assignement was to give students an opportunity to put in practice the implementation of an SMTP protocol.
The purpose of the written java code is to prank a group of selected victims (through their emails) and send them randomly selected hoax messages. The sender of the message would be among the victims. The application also CCs a selected witness (or a group of selected witnesses).
As it is implemented, the application evenly divides the group of selected victims and pranks each group with a different message.

The application was written and tested on windows plateform and still needs be be tested on other plateforms.

As it is configured now it uses a moockmock server (https://github.com/tweakers-dev/MockMock) to simulate the pranks. However, it should also work with a real smtp server provided that your internet service provider allows you to use the smtp protocol.

disclaimer
This application was written only for educational purpose and the author shall not be help accountable or responsible for any missuse of the proposed code.
