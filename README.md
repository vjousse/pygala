# Testing

    curl http://localhost:9000/highlight -X POST -F 'code=val=toto' -F 'lang=scala'

# Dependencies

You'll need solarized.py theme accessible from you pygment install.

# TODO
- Find a way to customize pygment in a clean way to find solarized.py file.
- Build a markdown extension for pygala. Current codehilite.py is limitated and have to be hacked in place.
