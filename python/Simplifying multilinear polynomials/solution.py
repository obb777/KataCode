def split_before (strng, delims):
    splitted = []
    split_item = ''
    for c in strng:
        if c in delims and len(split_item) > 0:
            splitted.append(split_item)
            split_item = ''
        split_item += c
    splitted.append(split_item)
    return splitted


def mysort (s1, s2):
    return cmp(s1, s2) if len(s1) == len(s2) else len(s1) - len(s2)


def simplify(poly):
    operands = split_before(poly, '+-')

    op_map = dict()
    for op in operands:
        mult_str = ''.join([c for c in op if c.isdigit() or c in '+-'])
        if len(mult_str) == 0 or (len(mult_str) == 1 and mult_str[0] in '+-'):
            mult_str += '1'
        mult = int(mult_str)

        vars = ''.join(sorted([c for c in op if not c.isdigit() and c not in '+-']))

        if vars in op_map:
            mult += op_map[vars]
        op_map[vars] = mult
        if mult == 0:
            del op_map[vars]

    res = ''
    for vars in sorted(op_map.keys(), cmp=mysort):
        coef = ('%+d' % op_map[vars])
        if len(coef) == 2:
            coef = coef.rstrip('1')
        res += coef + vars

    return res.lstrip('+')