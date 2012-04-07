# -*- coding: utf-8 -*-
import markdown
import argparse

def main():

    parser = argparse.ArgumentParser("Parse markdown with code highlighting.")
    parser.add_argument('-f', '--file', required=True)
    args = parser.parse_args()

    with open(args.file, 'r') as f:
        someText = f.read()

        html = markdown.markdown(someText, 
                ['codehilite(css_class=highlight, noclasses=True, force_linenos=True)']
                )

        print html

    f.closed

if __name__ == "__main__":
    main()
