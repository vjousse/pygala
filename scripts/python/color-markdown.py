# -*- coding: utf-8 -*-
import markdown
import argparse
import codecs

def main():

    parser = argparse.ArgumentParser("Parse markdown with code highlighting.")
    parser.add_argument('-f', '--file', required=True)
    args = parser.parse_args()

    with codecs.open(args.file, 'r', encoding='utf-8') as f:
        someText = f.read()

        html = markdown.markdown(someText, 
                ['codehilite(css_class=highlight, force_linenos=False, pygments_style=solarized)']
                )

        print html.encode('utf-8')

    f.closed

if __name__ == "__main__":
    main()
