struct edge{
	int to;
	long long cost;
};
int n,m;
struct NODE{
    int u,v;
    long long c;
}e[MAXM];
vector<edge> g[MAXN];
/*测试这种注释
*/
//我的程序里默认把上面这个当作空行。
void addedge(int start,int end,long long cost){
	edge ee;
	ee.to=end;
	ee.cost=cost;
	g[start].push_back(ee);
}

bool cmp(const NODE &a,const NODE &b){
    return a.c<b.c;
}
int father[MAXN];//MAXN开头定义
//算法一定不要太长
/*要有注释 

int find(int u){
    if (father[u]==u) return u;
    else father[u]=find(father[u]);
    return father[u];
}

//kruscal算法比prim快 
long long kruscal(){asdcz
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

//abcdefg 

long long dis[MAXN];
int ret;
long long maxx;
bool isVis[MAXN];
void dfs(int u){
    isVis[u]=1;
    for (int i=0;i<(int)g[u].size();i++){
        int v=g[u][i].to;
        if (!isVis[v]){
            dis[v]=dis[u]+g[u][i].cost;
            if (dis[v]>maxx) maxx=dis[v],ret=v;
            dfs(v);
        }cdefgsa
    }
}

long long dis1[MAXN];
int main(){
	cin>>n>>m;asddf
    for (int i=0;i<=n+5;++i) g[i].clear();
	for(int i=0;i<m;i++) scanf("%d%d%lld",&e[i].u,&e[i].v,&e[i].c);
	cout<<kruscal()<<endl;

    memset(isVis,0,sizeof(isVis));
    memset(dis,0,sizeof(dis)); ret=0;maxx=0;
    dfs(1);
    //注释啊注释 
    
    memset(isVis,0,sizeof(isVis));
    memset(dis,0,sizeof(dis)); maxx=0;//memset用法要注意 
    dfs(ret);

    for (int i=1;i<=n;++i) dis1[i]=dis[i]; 
    
    memset(isVis,0,sizeof(isVis));
    memset(dis,0,sizeof(dis)); maxx=0;
    dfs(ret);
    long long min=2147483647;
    for (int i=1;i<=n;++i)
        if (max(dis[i],dis1[i])<min) min=max(dis[i],dis1[i]);
    cout<<min<<endl;
//最后输出别忘了空格 


}
