import sys

def read_excel(path):
    """
    读取excel文件
    :return: 返回数据列表
    """
    pass

def write_excel(path, dataFrame, sheet_name):
    """
    将数据写入文件
    :param path: 写入文件完整路径
    :param dataFrame: 需要写入表格的数据
    :param sheet_name: sheet名称
    :return: 空
    """
    pass

def calculate_salary(list):
    """
    计算月薪
    :param list: 薪资构成列表[[收入项标题列表],[扣除项标题列表]]
    :return: 工资列表,扣款列表
    """
    pass

def calculate_social_insurance_personal(salary):
    """
    计算五险一金个人承担部分
    :param salary: [工资基数,公积金档位]列表
    :return: ("养老(个人)", "医疗(个人)", "失业(个人)", "工伤(个人)", "生育(个人)", "公积金(个人)", "合计(个人)")
    """
    pass

def calculate_social_insurance_corporate(salary):
    """
    计算五险一金企业承担部分
    :param salary: [工资基数,公积金档位]列表
    :return: ("养老(个人)", "医疗(个人)", "失业(个人)", "工伤(个人)", "生育(个人)", "公积金(个人)", "合计(个人)")
    """
    pass

def calculate_due_pay(list):
    """
    根据工资和扣款计算当月应发工资
    :param list: [工资列表,扣款列表]
    :return:
    """
    pass

def calculate_real_pay(list):
    """
    根据应发工资和五险一金以及个税计算实发工资
    :param list:
    :return:
    """
    pass

def calculate_tax(real_pay):
    """
    根据实发工资计算个税
    :param real_pay:
    :return: 计算个税
    """
    pass

def counting():
    """
    完成整个统计过程并将计算结果数据写入表格文件
    :return:
    """
    pass



def main(argv):
    pass


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    main(sys.argv)



