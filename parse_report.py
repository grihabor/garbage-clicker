import sys
import xml.etree.ElementTree as ET
from xmljson import parker
import yaml


def main(path):
    root = ET.parse(path).getroot()
    data = parker.data(root)
    print(yaml.dump(data))


if __name__ == '__main__':
    main(sys.argv[1])
    