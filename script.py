# This is a snake game made with Python Tkinter Library.

#!/usr/bin/python

# Simple button demo.
from Tkinter import *
import socket

# A better way is to create our fancy button by extending the Button class.
class HostnameLabel(Label):
    '''A Label widget which knows about an associated Entry widget, and can
       look up the host name it contains and display the corresponding IP'''

    # Look up host name in the assocated entry widget source, and display
    # in ourselves.
    def show(self):
        hn = self.source.get().strip()
        if hn == '':
            ip = ''
        else:
            try:
                ip = socket.gethostbyname(hn)
            except:
                ip = '[unknown]'
        self.configure(text=ip)

    def __init__(self, root, entry):
        Label.__init__(self, root, text="", width=15)
        self.source = entry
        

# Here are the colors.  These are just ordinary variables, and I need to
# remember to send them to all relevant widgets.  
bgcolor = '#AAAAFF'
actbgcolor = '#CCCCFF'
fgcolor = '#884400'

# Root and backing frame.
root = Tk()
fr = Frame(root, background=bgcolor)
fr.pack()

# Title label
tit = Label(fr, text="Host Name Lookup", background=bgcolor,
            foreground=fgcolor, relief='groove')
tit.grid(row=0, column=0, columnspan=2, sticky='news')

# Name entry.
entr = Entry(fr, width=25, background=bgcolor, foreground=fgcolor)
entr.grid(row=1, column=0, columnspan=2, sticky='news')

# Reporting label.
dislab = HostnameLabel(fr, entr)
dislab.configure(background=bgcolor, foreground=fgcolor)
dislab.grid(row=2, column=0, sticky='news')

# Go button.
but = Button(fr, text="Find", background=bgcolor, activebackground=actbgcolor,
             foreground=fgcolor, command=dislab.show)
but.grid(row=2, column=1, sticky='news')

root.mainloop()

# End of the program
