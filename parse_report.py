import sys
import xml.etree.ElementTree as ET
from xmljson import parker
import yaml
import json


def main(path):
    root = ET.parse(path).getroot()
    data = parker.data(root)
    print(json.dumps(data, indent=2))


if __name__ == '__main__':
    main(sys.argv[1])
    