import sys
import xml.etree.ElementTree as ET
from xmljson import parker
import yaml


def main(path):
    tree = ET.parse(path)
    data = parker.data(tree)
    print(yaml.dumps(data))


if __name__ == '__main__':
    main(sys.argv[1])
    