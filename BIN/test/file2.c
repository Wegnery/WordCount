int a
{//try
a
long long kruscal(){
    long long ret=0;
    sort(e,e+m,cmp);
    for (int i=1;i<=n;++i) father[i]=i;
    for (int i=0;i<m;++i){
        if (find(e[i].u)!=find(e[i].v)){
            father[find(e[i].u)]=find(e[i].v);
            addedge(e[i].u,e[i].v,e[i].c);
            addedge(e[i].v,e[i].u,e[i].c);
            ret+=e[i].c;
        }
    }
    return ret;
}
汉字有几个字符
a,   , a,


